package com.kozaxinan.ktlint

import com.pinterest.ktlint.core.Rule
import com.pinterest.ktlint.core.ast.ElementType
import com.pinterest.ktlint.core.ast.isPartOf
import com.pinterest.ktlint.core.ast.nextSibling
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.psi.KtObjectLiteralExpression

class OneEmptyLineAfterClassHeader : Rule("one-empty-line-after-class-header") {

  override fun visit(
      node: ASTNode,
      autoCorrect: Boolean,
      emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
  ) {
    if (node.elementType == ElementType.CLASS_BODY && !node.isPartOf(KtObjectLiteralExpression::class)) {
      val nextSibling = node.firstChildNode.nextSibling { true }
      if (nextSibling is PsiWhiteSpace) {
        val text = (nextSibling as PsiWhiteSpace).text
        val textWithoutSpaces = text.replace(" ", "")
        val hasOneEmptyLineAtBeginningOfClassBody = textWithoutSpaces == "\n\n"
        if (!hasOneEmptyLineAtBeginningOfClassBody) {
          emit(node.startOffset, "Expected to have one empty line after class header", true)

          if (autoCorrect) {
            val trimmedText = text.trim { it == '\n' }
            (nextSibling as LeafPsiElement).rawReplaceWithText("\n\n$trimmedText")
          }
        }
      }
    }
  }
}
