# @author abifadila on 08/12/2023
# @project Challenge Chapter 7 - Web Automation
# @for SYNRGY Academy Batch VI x BCA Group

@All
Feature: Sort Products
  As a user
  I want to sort the products by price
  So that I can easily find the products I want

  @Stage @Sort
  Scenario Outline: Sort products by <sortName>
    Given I am on the products page
    When I chose <sortName> and clicked option <value>
    Then the products should be sorted by <sortName> ascending order
    When I chose <sortName> and clicked option <value>
    Then the products should be sorted by <sortName> descending order
    When I chose <sortName> and clicked option <value>
    Then the products should be sorted by <sortName> ascending order
    When I chose <sortName> and clicked option <value>
    Then the products should be sorted by <sortName> descending order

    Examples:
    | sortName          | value |
    | Name A-Z          | az    |
    | Name Z-A          | za    |
    | Price Low to High | lohi  |
    | Price High to Low | hilo  |