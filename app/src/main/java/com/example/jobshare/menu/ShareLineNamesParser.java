package com.example.jobshare.menu;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ShareLineNamesParser {

	ShareLineItem objShareLineItem;
	List<ShareLineItem> listArray;

	public List<ShareLineItem> getData(String url) {

		try {

			listArray = new ArrayList<ShareLineItem>();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new URL(url).openStream());

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("item");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					objShareLineItem = new ShareLineItem();

					objShareLineItem.setId(getTagValue("id", eElement));
					objShareLineItem.setTitle(getTagValue("title", eElement));
					objShareLineItem.setDesc(getTagValue("desc", eElement));
					objShareLineItem.setPubdate(getTagValue("pubDate", eElement));
					objShareLineItem.setLink(getTagValue("link", eElement));

					listArray.add(objShareLineItem);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listArray;
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();

	}
}
