# @author abifadila on 08/12/2023
# @project Challenge Chapter 7 - Web Automation
# @for SYNRGY Academy Batch VI x BCA Group

@All
Feature: Login
  as a user
  i want to be able to access the Swag Labs website using correct login credentials
  but i don't want to be able to access the website if my credentials are wrong
  using Test Data Driven

  @Test @Login
  Scenario Outline: Login <result> scenario w/ user: <username>
    Given user is at loginPage
    When user fill <username> and <password>
    And user click loginButton
    Then user verify login <result>

    Examples:
      | username                | password     | result  |
      | standard_user           | secret_sauce | success |
      | locked_out_user         | secret_sauce | failed  |
      | problem_user            | secret_sauce | success  |
      | performance_glitch_user | secret_sauce | success  |
      | error_user              | secret_sauce | success  |
      | visual_user             | secret_sauce | success  |
      | standard_user           | wrong_pass   | failed  |