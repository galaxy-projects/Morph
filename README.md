<div align="center">
  <img src=".github/morph.png" alt="Morph logo" width="256"/>
</div>

![GitHub](https://img.shields.io/github/license/galaxy-projects/Morph)
![Java Version](https://img.shields.io/badge/Java-8%2B-blue)

### *Unified & Non-Destructive Configuration Loader for Java (JSON, YAML, Properties, XML…)*

Morph is a multi-version Java library (Java 8 → 25) that loads, merges, and saves configuration files in a **unified**, **non-destructive**, and **POJO-oriented** way.  
Through its modular provider system, Morph can handle multiple formats such as JSON, YAML, Properties, and XML while preserving comments and file structure.

---

## ✨ Features

### ✔️ Unified API for All Configuration Formats
Morph automatically detects the right provider based on the file extension:
- `.json`
- `.yml` / `.yaml`
- `.properties`
- `.xml`
- (+ pluggable custom providers)

### ✔️ POJO-First Configuration
Define configuration through simple Java classes:

```java
class ServerConfig {
    @Comment("Port used by the HTTP server")
    int port = 8080;

    String host = "localhost";
}
```

### ✔️ Advanced Comment Handling

Morph supports reading, preserving, merging, and rewriting comments via:

- MERGE — smart merge of file comments and POJO defaults
- OVERWRITE — replace file comments with POJO comments
- KEEP — never touch file comments

```java
@CommentStrategy(Strategy.MERGE)
class ServerConfig { ... }
```

### ✔️ Configurable Context (Morph Instance)

Each Morph instance carries its own context:
```java
Morph morph = Morph.builder()
    .workingDirectory(Paths.get("config"))
    .defaultCommentStrategy(Strategy.MERGE)
    .indentation(2)
    .build();
```

This avoids global state and allows multiple independent configuration contexts.

### ✔️ Non-Destructive Round-Trip

Morph guarantees:
- preservation of comments
- preservation of structural elements (depending on provider)
- stable load → modify → save cycles

### 🧩 Modular Architecture
```
  /morph
  ├─ core              → API, annotations, merge system, runtime discovery
  ├─ properties        → .properties support
  ├─ yaml-snakeyaml    → YAML support (SnakeYAML)
  ├─ json-gson         → JSON support (Gson)
  ├─ json-fasterxml    → JSON support (Jackson Databind)
  └─ xml-basic         → XML support using JDK
```

Providers are optional and detected at runtime.

### 🚀 Usage Example
```java
Morph morph = Morph.builder()
        .workingDirectory(Paths.get("config"))
        .indentation(2)
        .build();

AppConfig config = morph.load(AppConfig.class);

// modify the configuration
config.debug = true;

// save without losing formatting or comments
morph.save(config);
```
---

## 📦 Installation
```xml
Morph will be published on Maven Central.

<dependency>
    <groupId>org.galaxy.morph</groupId>
    <artifactId>morph</artifactId>
    <version>0.0.1</version>
</dependency>
```
Add providers as needed:

```xml
<dependency>
    <groupId>org.galaxy.morph</groupId>
    <artifactId>morph-provider-snakeyaml</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## 🧪 Testing
[README.md](../uniflow/README.md)
The project includes JUnit tests for:
- multi-format loading
- non-destructive merge
- smart comment merging
- round-trip stability
- provider fallback and detection

---

## 🤝 Contributing

Contributions are welcome—particularly:
- new providers
- enhanced comment merging
- improved YAML structure preservation

---

## 📄 License
MIT