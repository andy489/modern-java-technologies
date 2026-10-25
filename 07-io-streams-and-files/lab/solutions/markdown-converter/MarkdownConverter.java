package bg.sofia.uni.fmi.mjt.markdown;

import bg.sofia.uni.fmi.mjt.markdown.converter.BoldConverter;
import bg.sofia.uni.fmi.mjt.markdown.converter.CodeConverter;
import bg.sofia.uni.fmi.mjt.markdown.converter.Converter;
import bg.sofia.uni.fmi.mjt.markdown.converter.HeadersConverter;
import bg.sofia.uni.fmi.mjt.markdown.converter.ItalicConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownConverter implements MarkdownConverterAPI {

    private static final String OPENING_HTML_TAGS = """
            <html>
            <body>""";

    private static final String CLOSING_HTML_TAGS = """
            </body>
            </html>""";

    @Override
    public void convertMarkdown(Reader source, Writer output) {
        var reader = new BufferedReader(source);
        var writer = new PrintWriter(output);

        writer.println(OPENING_HTML_TAGS);

        Converter headerConverter = new HeadersConverter();
        List<Converter> formattingConverters = List.of(new BoldConverter(), new ItalicConverter(), new CodeConverter());

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    writer.println(line);
                    continue;
                }

                if (headerConverter.isApplicable(line)) {
                    line = headerConverter.apply(line);
                } else {
                    for (Converter converter : formattingConverters) {
                        if (converter.isApplicable(line)) {
                            line = converter.apply(line);
                        }
                    }
                }

                writer.println(line);
                writer.flush();
            }

            writer.println(CLOSING_HTML_TAGS);
            writer.flush();
        } catch (IOException ioExc) {
            throw new RuntimeException("Error occurred while processing markdown file", ioExc);
        }
    }

    @Override
    public void convertMarkdown(Path from, Path to) {
        try (var fileReader = Files.newBufferedReader(from);
             var fileWriter = Files.newBufferedWriter(to)) {
            convertMarkdown(fileReader, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while converting markdown file " + from.getFileName(), e);
        }
    }

    @Override
    public void convertAllMarkdownFiles(Path sourceDir, Path targetDir) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(sourceDir, "*.md")) {
            for (Path file : stream) {
                var sourceFileName = file.getFileName().toString();
                var nameWithoutExtension = sourceFileName.substring(0, sourceFileName.indexOf('.'));
                Path targetFile = targetDir.resolve(nameWithoutExtension + ".html");

                convertMarkdown(file, targetFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while converting markdown files from dir: " + sourceDir, e);
        }
    }

}