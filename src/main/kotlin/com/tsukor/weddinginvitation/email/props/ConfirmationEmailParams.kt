package com.tsukor.weddinginvitation.email.props

data class ConfirmationEmailParams(
    val coupleNames: String,
    val guestName: String,
    val confirmUrl: String,
    val expiresIn: String,
    val weddingDomain: String
)

object TemplateRenderer {

    private fun escapeHtml(s: String): String =
        s.replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#39;")

    fun renderHtml(template: String, p: ConfirmationEmailParams): String =
        template
            .replace("{{COUPLE_NAMES}}", escapeHtml(p.coupleNames))
            .replace("{{GUEST_NAME}}", escapeHtml(p.guestName))
            .replace("{{CONFIRM_URL}}", escapeHtml(p.confirmUrl))
            .replace("{{EXPIRES_IN}}", escapeHtml(p.expiresIn))
            .replace("{{WEDDING_DOMAIN}}", escapeHtml(p.weddingDomain))

    fun renderText(template: String, p: ConfirmationEmailParams): String =
        template
            .replace("{{COUPLE_NAMES}}", p.coupleNames)
            .replace("{{GUEST_NAME}}", p.guestName)
            .replace("{{CONFIRM_URL}}", p.confirmUrl)
            .replace("{{EXPIRES_IN}}", p.expiresIn)
            .replace("{{WEDDING_DOMAIN}}", p.weddingDomain)

    fun renderSubject(template: String, p: ConfirmationEmailParams): String =
        template.replace("{{COUPLE_NAMES}}", p.coupleNames)
}