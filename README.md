# DayNightSync

DayNightSync is a Minecraft plugin that synchronizes the in-game time with a real-world timezone, providing advanced control over time management in your server worlds.

## Features
- Synchronize Minecraft time with a specified timezone.
- Enable or disable the time synchronization system.
- Set the update interval for time synchronization.
- Optionally restrict synchronization to a specific world.
- Simple admin commands for reloading and toggling the system.

## Configuration
The plugin's configuration file is located at `src/main/resources/config.yml`. Below are the available configuration options:

```yaml
time:
  # The time zone to which the game is synchronized for timing, format: Region/City, Type: String.
  zone: "America/Bogota"
  # Whether the plugin is enabled or not, Type: Boolean.
  enable: false
  # Interval in seconds at which the in-game time will be updated, Type: Integer, Valid: 1 or more.
  update_interval: 5
  # If you specify a world, you can uncomment the following line to indicate a world where only the plugin applies.
  # If you leave it commented out, by default the plugin will apply the time to all worlds with NORMAL format, Type: String
  # world_name: "world"
```

### Configuration Options
- **time.zone**: The timezone to synchronize with (e.g., `America/Bogota`).
- **time.enable**: Enable (`true`) or disable (`false`) the time synchronization system.
- **time.update_interval**: How often (in seconds) the in-game time is updated.
- **time.world_name**: (Optional) The world where the plugin applies. If not set, applies to all NORMAL worlds.

### Search you TimeZone: https://www.zeitverschiebung.net/es/city/3688689

## Commands
The following commands are available for server administrators:

| Command                | Description                                      | Permission              |
|------------------------|--------------------------------------------------|-------------------------|
| `/daynight reload`     | Reloads the plugin configuration and system.     | daynightsync.admin      |
| `/daynight enable`     | Enables the time synchronization system.         | daynightsync.admin      |
| `/daynight disable`    | Disables the time synchronization system.        | daynightsync.admin      |

## Permissions
- `daynightsync.admin`: Required to use all plugin commands.

## Usage
1. Place the plugin JAR in your server's `plugins` folder.
2. Start the server to generate the default configuration.
3. Edit `config.yml` as needed and use `/daynight reload` to apply changes.

## License
This project is licensed under the MIT License.

