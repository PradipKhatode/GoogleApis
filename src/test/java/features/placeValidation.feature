Feature: Validating Place API's

  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlacesAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And  Verify the place_Id create maps to "<name>" using "getPlaceAPI"

Examples:
  | name    | language | address            |
  | AAhouse | English  | World cross center |
  #| FrontHouse | Spanish  | Sea cross center   |

  Scenario: Verify if the place is being deleted successfully;
    Given delete place payload
    When user calls "deletePlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
