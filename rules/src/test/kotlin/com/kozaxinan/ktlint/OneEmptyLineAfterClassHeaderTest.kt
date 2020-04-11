package com.kozaxinan.ktlint

import com.google.common.truth.Truth.assertThat
import com.pinterest.ktlint.core.LintError
import com.pinterest.ktlint.test.lint
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

internal class OneEmptyLineAfterClassHeaderTest : Spek({

  describe("one-empty-line-after-class-header rule") {

    // whenever KTLINT_DEBUG env variable is set to "ast" or -DktlintDebug=ast is used
    // com.pinterest.ktlint.test.(lint|format) will print AST (along with other debug info) to the stderr.
    // this can be extremely helpful while writing and testing rules.
    // uncomment the line below to take a quick look at it
    System.setProperty("ktlintDebug", "ast")

    val rule = OneEmptyLineAfterClassHeader()

    it("should do nothing for one empty line after class header") {
      val lint = rule.lint(
          """ |class foo {
                  |
                  |   val v = "val"
                  |}""".trimMargin()
      )
      assertThat(lint).isEmpty()
    }

    it("should detect no empty line after class header") {
      val lint = rule.lint(
          """ |class foo {
              |   val v = "val"
              |}""".trimMargin()
      )
      assertThat(lint).isEqualTo(listOf(LintError(1, 11, "one-empty-line-after-class-header", "Expected to have one empty line after class header", true)))
    }

    it("should detect more than one empty line after class header") {
      val lint = rule.lint(
          """ |class foo {
              |
              |
              |   val v = "val"
              |}""".trimMargin()
      )
      assertThat(lint).isEqualTo(listOf(LintError(1, 11, "one-empty-line-after-class-header", "Expected to have one empty line after class header", true)))
    }

    it("should ignore object literals") {
      val lint = rule.lint(
          """ |class foo {
              |
              |interface Listener {
              |
              |    fun onValueChanged(criteria: Criteria, value: Valuable?)
              |
              |    companion object {
              |
              |      @JvmField
              |      val NO_OP: Listener = object : Listener {
              |        override fun onValueChanged(criteria: Criteria, value: Valuable?) = Unit
              |      }
              |    }
              |  }
              |}""".trimMargin()
      )
      assertThat(lint).isEmpty()
    }
  }
})
