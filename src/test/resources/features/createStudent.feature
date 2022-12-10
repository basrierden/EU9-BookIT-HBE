Feature: Create student


  @wip
  Scenario: Create student as a teacher and verify status code 201
    Given I logged Bookit api using "blyst6@si.edu" and "barbabaslyst"
    When I send POST request to "api/students/student" endpoint with following information
      | first-name      | Tarik                  |
      | last-name       | Cetin                  |
      | email           | tarikcetin57@gmail.com |
      | password        | abc12345               |
      | role            | student-team-leader    |
      | campus-location | VA                     |
      | batch-number    | 8                      |
      | team-name       | Nukes                  |
    Then status code should be 201
    And I delete previously added student

  @wipHasan
  Scenario: Test config
    Given I get env properties

  @wip
  Scenario: Create student a teacher and verify status code 201
    Given I logged Bookit api as "teacher"
    When I send POST request to "api/students/student" endpoint with following information
      | first-name      | Tarik                  |
      | last-name       | Cetin                  |
      | email           | tarikcetin56@gmail.com |
      | password        | abc12345               |
      | role            | student-team-leader    |
      | campus-location | VA                     |
      | batch-number    | 8                      |
      | team-name       | Nukes                  |
    Then status code should be 201
    And I delete previously added student

