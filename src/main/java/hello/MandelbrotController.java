package hello;

import mandelbrot.MandelbrotComputer;
import mandelbrot.MandelbrotRenderer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class MandelbrotController {
    @RequestMapping("/mandelbrot")
    public String mandelbrot(
            @RequestParam(value = "width", required = false, defaultValue = "1024") int width,
            @RequestParam(value = "height", required = false, defaultValue = "1024") int height,
            @RequestParam(value = "top", required = false, defaultValue = "1.25") double top,
            @RequestParam(value = "right", required = false, defaultValue = "0.5") double right,
            @RequestParam(value = "bottom", required = false, defaultValue = "-1.25") double bottom,
            @RequestParam(value = "left", required = false, defaultValue = "-2") double left,
            @RequestParam(value = "precision", required = false, defaultValue = "1024") int precision,
            Model model) throws IOException {
        int[][] mandelbrotSet = new MandelbrotComputer(precision).compute(width, height, top, right, bottom, left);
        String image = new MandelbrotRenderer().render(mandelbrotSet, precision);

        model
                .addAttribute("renderedImage", image)
                .addAttribute("width", width)
                .addAttribute("height", height)
                .addAttribute("top", top)
                .addAttribute("right", right)
                .addAttribute("bottom", bottom)
                .addAttribute("left", left)
                .addAttribute("precision", precision);

        return "mandelbrot";
    }

}
