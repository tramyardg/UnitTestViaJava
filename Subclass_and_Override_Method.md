# Subclass and Override Method

The central idea of Subclass and Override Method is that you can use inheritance in the context of a test to nullify behavior that you don’t care about or get access to behavior that you do care about.

Let’s take a look at a method in a little application:
```java
class MessageForwarder {
    private Message createForwardMessage(Session session, Message message) throws MessagingException, IOException {
        MimeMessage forward = new MimeMessage(session);
        forward.setFrom(getFromAddress(message));
        forward.setReplyTo(new Address [] { new InternetAddress(listAddress) });
        forward.addRecipients(Message.RecipientType.TO, listAddress);
        forward.addRecipients(Message.RecipientType.BCC, getMailListAddresses());
        forward.setSubject(transformedSubject(message.getSubject()));
        forward.setSentDate(message.getSentDate ());
        forward.addHeader(LOOP_HEADER, listAddress);
        buildForwardContent(message, forward);
        return forward;
    }
    …
}
```

Let’s suppose that we don’t want to have a dependency on the `MimeMessage` class when we are testing. It uses a variable named `session`, and we will not have a real `session` when we are testing. If we want to separate out the dependency on `MimeMessage`, we can make `createForwardMessage` protected and override it in a new subclass that we make just for testing:

```java
class TestingMessageForwarder extends MessageForwarder {
    protected Message createForwardMessage(Session session, Message message) {
        Message forward = new FakeMessage(message);
        return forward;
    }
    …
}
```

In this new subclass, we can do whatever we need to do to get the separation or the sensing that we need. In this case, we are essentially nulling out most of the behavior of `createForwardMessage`, but if we don’t need it for the particular thing that we are testing right now, that can be fine. 

In production code, we instantiate `MessageForwarders`; in tests, we instantiate `TestingMessageForwarders`. We were able to get separation with minimal modification of the production code. All we did was change the scope of a method from private to protected.
