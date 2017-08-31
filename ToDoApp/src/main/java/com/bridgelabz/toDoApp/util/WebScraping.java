package com.bridgelabz.toDoApp.util;


import java.net.URI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.toDoApp.model.WebScrap;

@Component
public class WebScraping {
	@Autowired
	WebScrap scrap;

	public void webScraping(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			String title = null;
			String image = null;
			URI uri = new URI(url);
			String host = uri.getHost();
			System.out.println("host name"+host);
			Elements elements = doc.select(("meta[property=og:title]"));
			Elements elements2 = doc.select(("meta[property=og:image]"));
			if (elements != null) {
				title = elements.attr("content");
			} else {
				title = doc.text();
			}
			if (elements2 != null) {
				image = elements2.attr("content");
			}
			scrap.setTitle(title);
			scrap.setImage(image);
			scrap.setHost(host);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
