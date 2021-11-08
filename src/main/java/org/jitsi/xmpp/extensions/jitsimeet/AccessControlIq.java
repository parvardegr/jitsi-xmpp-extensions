package org.jitsi.xmpp.extensions.jitsimeet;

import org.jivesoftware.smack.packet.IQ;
import org.jxmpp.jid.Jid;

/**
 * IQ used for media and chat access management of participants in Jitsi Meet
 * conferences.
 *
 * @author Amir Pirhosseinloo
 */
public class AccessControlIq extends IQ {
    /**
     * Name space of access-control packet extension.
     */
    public static final String NAMESPACE = "https://gharar.ir/access-control";

    /**
     * XML element name of access-control packet extension.
     */
    public static final String ELEMENT_NAME = "access-control";

    /**
     * Attribute name of "target".
     */
    public static final String TARGET_ATTR_NAME = "target";

    /**
     * Attribute name of "actor".
     */
    public static final String ACTOR_ATTR_NAME = "actor";

    /**
     * Attribute name of "resource".
     */
    public static final String RESOURCE_ATTR_NAME = "resource";

    /**
     * Attribute name of "action".
     */
    public static final String ACTION_ATTR_NAME = "action";

    /**
     * Attribute name of "payload".
     */
    public static final String PAYLOAD_ATTR_NAME = "payload";

    /**
     * The target's jid to perform the action on.
     */
    private Jid targetJid;

    /**
     * The jid of the peer that initiated the request.
     */
    private Jid actorJid;

    /**
     * The action to perform.
     */
    private String action;

    /**
     * The resource to perform the action on.
     */
    private String resource;

    /**
     * The payload of the iq. Represents extra info.
     */

    private String payload;

    /**
     * Creates a new instance of this class.
     */
    public AccessControlIq() {
        super(ELEMENT_NAME, NAMESPACE);
    }

    @Override
    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder xml) {
        if (targetJid != null) {
            xml.attribute(TARGET_ATTR_NAME, targetJid);
        }

        if (actorJid != null) {
            xml.attribute(ACTOR_ATTR_NAME, actorJid);
        }

        if (action != null) {
            xml.attribute(ACTION_ATTR_NAME, action);
        }

        if (resource != null) {
            xml.attribute(RESOURCE_ATTR_NAME, resource);
        }

        xml.rightAngleBracket().append(getPayload());

        return xml;
    }

    /**
     * Sets the target's jid.
     *
     * @param jid the jid of the target to perform the action on.
     */
    public void setTargetJid(Jid jid) {
        this.targetJid = jid;
    }

    /**
     * Returns the target's jid.
     *
     * @return the jid of the target to perform the action on.
     */
    public Jid getTargetJid() {
        return targetJid;
    }

    /**
     * Sets the jid for the peer that initiated the request.
     *
     * @param jid the jid of the peer doing the request.
     */
    public void setActorJid(Jid jid) {
        this.actorJid = jid;
    }

    /**
     * Returns the peer's jid that initiated the request.
     *
     * @return the peer's jid that initiated the request.
     */
    public Jid getActorJid() {
        return actorJid;
    }

    /**
     * Sets the action to be performed on the target. ex: grant.
     *
     * @param action the action to be performed on the target.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Returns the action to be performed on target. ex: grant.
     *
     * @param action the action to be performed on the target.
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the resource which the action is performed on. ex: media.
     *
     * @param resource the resource which the action is performed on.
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * Returns the resource which the action is performed on. ex: media.
     *
     * @param resource the resource which the action is performed on.
     */
    public String getResource() {
        return resource;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
