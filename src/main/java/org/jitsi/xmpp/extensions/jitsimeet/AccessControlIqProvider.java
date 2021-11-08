package org.jitsi.xmpp.extensions.jitsimeet;

import org.jivesoftware.smack.provider.*;

import org.jxmpp.jid.*;
import org.jxmpp.jid.impl.*;
import org.xmlpull.v1.*;

/**
 * The parser of {@link AccessControlIq}.
 *
 * @author Amir Pirhosseinloo
 */
public class AccessControlIqProvider
    extends IQProvider<AccessControlIq>
{
    public static void registerAccessControlIqProvider()
    {
        ProviderManager.addIQProvider(
            AccessControlIq.ELEMENT_NAME,
            AccessControlIq.NAMESPACE,
            new AccessControlIqProvider());
    }

    @Override
    public AccessControlIq parse(XmlPullParser parser, int initialDepth)
        throws Exception
    {
        String namespace = parser.getNamespace();

        // Check the namespace
        if (!AccessControlIq.NAMESPACE.equals(namespace))
        {
            return null;
        }

        String rootElement = parser.getName();

        AccessControlIq iq;

        if (AccessControlIq.ELEMENT_NAME.equals(rootElement))
        {
            iq = new AccessControlIq();
            String targetJidStr = parser.getAttributeValue("", AccessControlIq.TARGET_ATTR_NAME);
            if (targetJidStr != null)
            {
                Jid targetJid = JidCreate.from(targetJidStr);
                iq.setTargetJid(targetJid);
            }

            String actorJidStr
                = parser.getAttributeValue("", AccessControlIq.ACTOR_ATTR_NAME);
            if (actorJidStr != null)
            {
                Jid actorJid = JidCreate.from(actorJidStr);
                iq.setActorJid(actorJid);
            }

            String action = parser.getAttributeValue("", AccessControlIq.ACTION_ATTR_NAME);
            if (action != null)
            {
                iq.setAction(action);
            }

            String resource = parser.getAttributeValue("", AccessControlIq.RESOURCE_ATTR_NAME);
            if (resource != null)
            {
                iq.setResource(resource);
            }
        }
        else
        {
            return null;
        }

        boolean done = false;

        while (!done)
        {
            switch (parser.next())
            {
                case XmlPullParser.END_TAG:
                {
                    String name = parser.getName();

                    if (rootElement.equals(name))
                    {
                        done = true;
                    }
                    break;
                }

                case XmlPullParser.TEXT:
                {
                    String payload = parser.getText();
                    iq.setPayload(payload);
                    break;
                }
            }
        }

        return iq;
    }
}