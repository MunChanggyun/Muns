package kr.or.kosta.Service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;


public class MemberPwdSendEmailService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward =new ActionForward();
		String member_id = (String) request.getAttribute("member_id");
		String member_pw = (String) request.getAttribute("member_pw");
		
				//보내는 사람
				String smtpServer = "smtp.naver.com";
				final String sendId ="qwerleo1234";
				final String sendPass = "Pigcow5678";
				String sendEmailAddress = "qwerleo1234@naver.com";
				int smtpPort=465;
				
				//받는 사람
				String recieveEmailAddress = member_id;
				String subject = "테스트 메일";
				String content =  "당신의 비밀번호는 [" + member_pw + "] 입니다";
				
				Properties props = System.getProperties();
				
				props.put("mail.smtp.host", smtpServer);
				props.put("mail.smtp.port", smtpPort);
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.ssl.enable", "true");
				props.put("mail.smtp.ssl.trust", smtpServer);
				
				Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
					protected PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication(sendId, sendPass);
					}
				});
				
				session.setDebug(true); //for debug
				
				Message mimeMessage = new MimeMessage(session);
				
				try {
					mimeMessage.setFrom(new InternetAddress(sendEmailAddress));
					mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recieveEmailAddress));
					mimeMessage.setSubject(subject);
					mimeMessage.setText(content);
					Transport.send(mimeMessage);
					
				} catch (Exception e) {
					e.printStackTrace();
				} 

				forward.setRedirect(false);
				forward.setPath("/view/member/login.jsp");
				
		return forward;
		

	}

}
