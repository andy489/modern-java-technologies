# Markdown Converter

We will build a tool for converting files in [Markdown](https://www.markdownguide.org/getting-started/) format to [HTML](https://www.w3schools.com/html/) format.

Markdown is a popular lightweight markup language that lets you add formatting to plain-text documents. It is widely used for web pages, documentation, notes, books, presentations, emails, and technical documentation. Popular web apps such as [Reddit](https://www.reddit.com) and [GitHub](https://github.com) support Markdown.

HTML (HyperText Markup Language) is the standard markup language for creating web pages. Markdown documents are conveniently rendered in a formatted view in a web browser after conversion to HTML.

## Supported Markdown Syntax

Our tool will support a limited subset of Markdown elements.

### Headings

| Markdown               | HTML                       |
| :--------------------- | :------------------------- |
| `# Heading level 1`   | `<h1>Heading level 1</h1>` |
| `## Heading level 2`  | `<h2>Heading level 2</h2>` |
| `### Heading level 3` | `<h3>Heading level 3</h3>` |
| `#### Heading level 4`| `<h4>Heading level 4</h4>` |
| `##### Heading level 5`| `<h5>Heading level 5</h5>` |
| `###### Heading level 6`| `<h6>Heading level 6</h6>` |

### Emphasis: Bold

| Markdown                      | HTML                                      |
| :---------------------------- | :---------------------------------------- |
| `I just love **bold text**.`  | `I just love <strong>bold text</strong>.` |
| `Love**is**bold`              | `Love<strong>is</strong>bold`             |

### Emphasis: Italic

| Markdown                              | HTML                                          |
| :------------------------------------ | :-------------------------------------------- |
| `Italicized text is the *cat's meow*.` | `Italicized text is the <em>cat's meow</em>.` |
| `A*cat*meow`                          | `A<em>cat</em>meow`                           |

### Inline Code

| Markdown                          | HTML                                        |
| :-------------------------------- | :------------------------------------------ |
| ``Always `.close()` your streams``| `Always <code>.close()</code> your streams` |

Enclosed content is always within a single line. For simplicity, assume each line contains at most one occurrence of each formatting element.  
A single line may combine `bold`, `italic`, and `code` fragments, but without nesting:

| Markdown                            | HTML                                                        |
| :---------------------------------- | :---------------------------------------------------------- |
| `` `.close()` *your* **eyes** ``    | `<code>.close()</code> <em>your</em> <strong>eyes</strong>` |

## Output HTML Structure

The tool generates a minimal HTML document where the converted Markdown content is wrapped in `<html>` and `<body>` tags. No other tags are generated except `h1`–`h6`, `strong`, `em`, and `code`:

```html
<html>
<body>
Converted Markdown content here
</body>
</html>
```

## Markdown Converter API

In the package `bg.sofia.uni.fmi.mjt.markdown`, create a class `MarkdownConverter` with a public no-args constructor that implements the following interface:

```java
package bg.sofia.uni.fmi.mjt.markdown;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;

public interface MarkdownConverterAPI {

    /** Converts Markdown from {@code source} and writes HTML to {@code output}. */
    void convertMarkdown(Reader source, Writer output);

    /** Converts a Markdown file to an HTML file. */
    void convertMarkdown(Path from, Path to);

    /**
     * Converts all .md files in {@code sourceDir} to .html files in {@code targetDir}.
     * The source directory contains no subdirectories.
     */
    void convertAllMarkdownFiles(Path sourceDir, Path targetDir);
}
```

## Notes

- Input Markdown files will always be well-formed. You do not need to validate them.
- Each supported conversion applies within a single line, so file processing is line-by-line and independent of surrounding lines.

## Testing

Test the converter manually first, then write unit tests with good code coverage.  
Use in-memory streams (e.g. `StringReader`/`StringWriter`) instead of static files where possible. For file-based tests, create and delete directories/files dynamically — do not rely on pre-existing files.

## Package Structure

```
src
└── bg.sofia.uni.fmi.mjt.markdown
    ├── MarkdownConverter.java
    └── MarkdownConverterAPI.java

test
└─ bg.sofia.uni.fmi.mjt.markdown
    └─ MarkdownConverterTest.java
```
