package com.hshim.note.controller.page

import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class NotFoundController : ErrorController {

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest, model: Model, redirectAttributes: RedirectAttributes): String {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
        val requestURI = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI) as? String

        if (status != null && status.toString() == HttpStatus.NOT_FOUND.value().toString()) {
            // 공유 코드 URL인 경우 메인 화면으로 리다이렉트하고 메시지 전달
            if (requestURI?.startsWith("/page/share/") == true) {
                redirectAttributes.addFlashAttribute("errorMessage", "찾을 수 없거나 만료된 코드입니다")
                return "redirect:/"
            }
            // 그 외 404 에러는 일반 에러 페이지 표시
            model.addAttribute("errorMessage", "찾을 수 없거나 만료된 코드입니다")
        }

        return "error"
    }
}
