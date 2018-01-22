package cn.e3mall.search.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import cn.e3mall.search.service.SearchItemService;

public class ItemChangeListener implements MessageListener {

	@Autowired
	private SearchItemService searchItemServiceImpl;
	
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		Long itemId = null;
		TextMessage textMessage=null;
		try {
			// 取商品id
			if (message instanceof TextMessage) {
				textMessage = (TextMessage) message;
				itemId = Long.parseLong(textMessage.getText());
			}
			// 向索引库添加文档
			searchItemServiceImpl.addDocument(itemId);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
