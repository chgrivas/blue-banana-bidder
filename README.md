# blue-banana-bidder
A simple, yet functional, real-time bid handler.

## Usage

### Run application

Use `./gradlew bootRun` to run the application locally.

So a POST at: `localhost/bid` with the appropriate body can trigger the bidding-campaign matching process.

### Build artifacts

Use `./gradlew build` to build the application artifacts.

### Clean application

Use `./gradlew clean` to clean the application artifacts.

### Run integration tests

Use `./gradlew test` to run the functional tests with the required test cases.

## Technical description

Spring boot was used to create a rest API to serve the blue banana bidder requirements. Integration layer tests have
been added together with wiremock that has simulated the 3rd party campaign API in order properly test the API functionality.

The basic implementation choices were:
- spring boot because it is easy to setup without boilerplate code
- Groovy & spock framework for quick and clear tests

# TODO

- Add more unit tests

# Q&A

For questions please contact Christos Grivas <chgrivas7@gmail.com>
