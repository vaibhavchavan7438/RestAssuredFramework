Feature: Add place, delete place and get place API

Scenario Outline: To check whethe add place API works fine
Given Add place API with "<name>", "<address>" and "<language>" parameters
When API "AddPlaceAPI" is fired with "POST" http request
Then success response with status code 200
And "scope" is equal to "APP"
And "status" is equal to "OK"

Examples:
| name    | address          | language |
| Vaibhav | At Post Devwadi  | Marathi  |
| Adam    | Times square, UK | English  |


Scenario: To check whethe get place API works fine
Given getPlaceAPI with place_id key
When API "getPlaceAPI" is fired with "GET" http request
Then success response with status code 200
And "website" is equal to "www.coolmonks.com/"
And "accuracy" is equal to "50"