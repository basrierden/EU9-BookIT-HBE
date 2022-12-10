Feature: Self Test Scripts

  @wip @ui
  Scenario: Verify teacher info from API vs UI
    Given I logged BookIt API using "blyst6@si.edu" and "barbabaslyst"
    When I get the current user information from BookIt API
    And API teacher status code should be 200
    And user logs in using BookIt web site using "blyst6@si.edu" and "barbabaslyst"
    And user goes to the my self page
    Then UI and API teacher information must be match

  @wip @ui
  Scenario: Verify posted info from API vs UI
    Given I logged BookIt API using "blyst6@si.edu" and "barbabaslyst"
    When I send POST request to BookIt API with following information
      | first-name      | Tarik                  |
      | last-name       | Cetin                  |
      | email           | tarikcetin58@gmail.com |
      | password        | abc00123               |
      | role            | student-team-leader    |
      | campus-location | VA                     |
      | batch-number    | 8                      |
      | team-name       | Nukes                  |
    And API student status code should be 201
    And user logs in using BookIt web site using student email "tarikcetin58@gmail.com" and password "abc00123"
    And user goes to the my self page
    Then UI and API user information must be match