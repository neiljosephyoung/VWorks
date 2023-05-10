package com.example.VWorks.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptPreProcessor;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Service
public class WebToolsService {

    public String scalpWebsite(String body) {
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX);

        try {
            HtmlPage page = webClient.getPage("https://www.rte.ie/");

            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();

            String title = page.getTitleText();
            System.out.println("Page Title: " + title);

            List<?> anchors = page.getByXPath("//a[@class='card-link']");
            for (int i = 0; i < anchors.size(); i++) {
                HtmlAnchor link = (HtmlAnchor) anchors.get(i);
                String recipeTitle = link.getAttribute("title").replace(',', ';');
                String recipeLink = link.getHrefAttribute();
                System.out.println(recipeTitle+" : "+recipeLink);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }
        return "ok";
    }

    public class MyScriptPreProcessor implements ScriptPreProcessor {
        @Override
        public String preProcess(HtmlPage htmlPage, String source, String pageUrl, int lineNumber, HtmlElement htmlElement) {
            return source.replaceAll("identifier", "myIdentifier");
        }

    }

}
