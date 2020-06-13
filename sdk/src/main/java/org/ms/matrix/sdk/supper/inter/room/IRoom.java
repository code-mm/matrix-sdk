package org.ms.matrix.sdk.supper.inter.room;

import org.ms.matrix.sdk.client.MatrixClient;
import org.ms.matrix.sdk.model.MessageModel;
import org.ms.matrix.sdk.model.RoomJoinedUserInfo;
import org.ms.matrix.sdk.model.event.m_call_answer;
import org.ms.matrix.sdk.model.event.m_call_candidates;
import org.ms.matrix.sdk.model.event.m_call_invite;
import org.ms.matrix.sdk.model.event.m_text;
import org.ms.matrix.sdk.model.message.TextModel;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;

import java.util.List;

public interface IRoom {

    /**
     * 回去房间ID
     *
     * @return
     */
    String getRoomId();


    /**
     * 获取房间名称
     *
     * @return
     */
    void getRoomAliases(MatrixCallBack callBack);


    /**
     * 发送消息
     *
     * @param messageModel
     * @param callBack
     */
    void send(MessageModel messageModel, MatrixCallBack callBack);


    void sendText(m_text param, MatrixCallBack callBack);

    void sendCallInvite(m_call_invite.Invtie param, MatrixCallBack callBack);

    void sendCallCandidates(m_call_candidates.Candidates param, MatrixCallBack callBack);

    void sendCallAnswer(m_call_answer.Content param,MatrixCallBack callBack);

                        /**
     * 获取加入房间的用户详细信息列表
     *
     * @param callBack
     */
    void getJoinedMembers(MatrixCallBack<List<RoomJoinedUserInfo>, Throwable> callBack);


}
