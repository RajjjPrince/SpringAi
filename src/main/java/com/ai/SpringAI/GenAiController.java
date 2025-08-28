package com.ai.SpringAI;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GenAiController<quality> {

    ChatService chatService;

    public GenAiController(ImageService imageService) {
        this.imageService = imageService;
    }

    ImageService imageService;

    public GenAiController(ChatService chatService) {
        this.chatService = chatService;
    }
    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("/test-ai")
    public String testAi() {
        return "ChatModel is injected: " + (chatService != null);
    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }

//    @GetMapping("generate-image")
//    public void generateImages(HttpServletResponse response, @RequestParam String prompt) throws IOException {
//         ImageResponse imageResponse= imageService.generateImage(prompt);
//         String imageUrl = imageResponse.getResult().getOutput().getUrl();
//         response.sendRedirect(imageUrl);
//    }

    @GetMapping("generate-image")
    public List<String> generateImages(HttpServletResponse response,
                                       @RequestParam String prompt,
                                       @RequestParam(defaultValue ="hd")String quality,
                                       @RequestParam(defaultValue ="1")int n,
                                       @RequestParam(defaultValue ="1024") int height,
                                       @RequestParam(defaultValue ="hd")int width
                                       ) throws IOException {
         ImageResponse imageResponse= imageService.generateImage(prompt,quality,n,height,width);
         List<String> imageUrl = imageResponse.getResults().stream()
                         .map(result -> result.getOutput().getUrl())
                                 .collect(Collectors.toList());
         return imageUrl;

    }

}
