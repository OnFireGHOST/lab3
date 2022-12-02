package com.example.demo.controllers

import com.example.demo.services.CalculatorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("calc")
class CalculatorController(
    private val calculateService: CalculatorService
) {

    @GetMapping(params = ["a", "b", "op"])
    fun calculate(
        @RequestParam("a") a: Double,
        @RequestParam("b") b: Double,
        @RequestParam("op") operation: String
    ): String {
        val res = when (operation) {
            "plus" -> calculateService.plus(a, b)
            "sub" -> calculateService.subtract(a, b)
            "mul" -> calculateService.multiply(a, b)
            "div" -> {
                try {
                    calculateService.divide(a, b)
                } catch (ex: IllegalArgumentException) {
                    ex.message
                }
            }
            else -> "Неизвестная операция"
        }
        return res.toString()
    }

    @GetMapping("/test")
    fun test(): String {
        return "Sec"
    }
}