@smoke
Feature: User Verification

#@wip
  Scenario: verify information about logged user
    Given I logged Bookit api using "blyst6@si.edu" and "barbabaslyst"
    When I get the current user information from api
    Then status code should be 200

  @wip
  Scenario: verify information about logged user from api and database
    Given I logged Bookit api using "blyst6@si.edu" and "barbabaslyst"
    When I get the current user information from api
    Then the information about current user from api and database should match

  @db @ui
  Scenario: three point verification (UI,API,Database)
    Given user logs in using "blyst6@si.edu" "barbabaslyst"
    And  user is on the my self page
    Given I logged Bookit api using "blyst6@si.edu" and "barbabaslyst"
    When I get the current user information from api
    Then UI,API and Database user information must be match

  @db @ui
  Scenario Outline: three point verification (UI,API,Database) DDT
    Given user logs in using "<email>" "<password>"
    And  user is on the my self page
    Given I logged Bookit api using "<email>" and "<password>"
    When I get the current user information from api
    Then UI,API and Database user information must be match

    Examples:
      | email              | password         |
      | blyst6@si.edu | barbabaslyst |
      #| nshearsby7w@uiuc.edu | lorettebradnum |
