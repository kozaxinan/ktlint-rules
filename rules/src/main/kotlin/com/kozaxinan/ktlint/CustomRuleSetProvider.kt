package com.kozaxinan.ktlint

import com.pinterest.ktlint.core.RuleSet
import com.pinterest.ktlint.core.RuleSetProvider

class CustomRuleSetProvider : RuleSetProvider {

    override fun get(): RuleSet = RuleSet("kozaxinan-ktlint-custom-rules", OneEmptyLineAfterClassHeader())
}
