# DiscordAutoReply

A lightweight Paper plugin for the BenSmP Minecraft server.

## What it does

When a player sends a chat message containing the standalone word `dc` or `discord` (case-insensitive), the plugin broadcasts this exact message to everyone online:

```text
[BenSmP]Discord:https://discord.gg/TrhuutnjF
```

Examples that trigger it: `dc`, `Discord?`, `discord link`, `dc var mı`.

## Compatibility

- Paper 1.21.11
- Java 21

## Install

1. Download the `DiscordAutoReply-1.0.0.jar` file from the GitHub Actions artifact.
2. Put it in the server's `plugins` folder.
3. Restart the Paper server.

## Build locally

```bash
mvn clean package
```

The JAR is created in `target/DiscordAutoReply-1.0.0.jar`.

GitHub Actions builds the plugin on every push or pull request to `main`, and also supports manual runs.
