*** Settings ***
Library    package ru.stqa.pft.addressbook.rf.AddressbookKeywords
Suite Setup    Init Application Manager
Suite Teardown    Stop Application Manager


*** Test Cases ***
Can Create Group With Valid Data
    ${old_count} =    Get Group Count
    Create Group    test name    test header    test footer
    ${new_count} =    Get Group Count
    ${expected_count} =    Evaluate    ${old_count} + 1
    Should Be Equal As Integer    ${new_count}    ${expected_count}
