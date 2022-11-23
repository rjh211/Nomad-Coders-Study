package hello.typeconverter.controller;

import hello.typeconverter.Type.IpPort;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConverterController {

    @GetMapping("/converter-view")
    public String converterView(Model model){
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", new IpPort("127.0.0.1", 8080));
        return "converter-view";
    }

    @GetMapping("converter/edit")
    public String converterForm(Model model){
        IpPort ipPort =  new IpPort("127.0.0.1", 8080);
        Form form = new Form(ipPort);
        model.addAttribute("form", form);
        return "converter-form";
    }

    @PostMapping("converter/edit")
    public String converterEdit(@ModelAttribute Form form, Model model){
        //화면에서 submit을 하면 ipPort 객체가 Form형태로 저장 되어야 하는데, 이경우에도 IpPortToStringConverter가 작동하게된다.
        IpPort ipPort = form.getIpPort();
        model.addAttribute("ipPort", ipPort);
        return "converter-view";
    }

    @Data
    static class Form{
        private IpPort ipPort;

        public Form(IpPort ipPort){
            this.ipPort = ipPort;
        }
    }
}
