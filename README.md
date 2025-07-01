# Personal Website

This is the source code for my personal website built using Kotlin.

## Project Structure

The project is organised as follows:

- `src/main/kotlin/pages/`: Pages
- `src/main/kotlin/components/`: Common components used across pages

## Technologies Used

- **Kotlin**: The primary programming language used for the website.
- **Kotlinx Html**: A DSL for building HTML in Kotlin.
- **Pico Css**: Minimal CSS framework for semantic HTML.

## How to Run

1. Clone the repository:
    ```sh
    git clone https://github.com/Apollointhehouse/Apollointhehouse-Web
    cd personal-website
    ```

2. Build the project using Gradle:
    ```sh
    ./gradlew build
    ```

3. Run the project:
    ```sh
    java -jar .\build\libs\Apollointhehouse-Web-1.0-SNAPSHOT.jar PAT
    ```

## License

This project is licensed under the MIT Licence. See the [LICENCE](LICENSE) file for details.
