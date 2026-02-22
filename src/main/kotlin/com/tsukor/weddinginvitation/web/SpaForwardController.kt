package com.tsukor.weddinginvitation.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SpaForwardController {
    @GetMapping(value = ["/{path:[^\\.]*}", "/**/{path:[^\\.]*}"])
    fun forward(): String = "forward:/index.html"
}