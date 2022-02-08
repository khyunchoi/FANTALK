package com.ssafy.api.service;


import com.ssafy.api.request.MeetingRegisterPostReq;
import com.ssafy.api.response.MeetingDetailGetRes;
import com.ssafy.db.entity.Meeting;
import com.ssafy.db.entity.User;

import java.util.List;

/**
 * 팬미팅 서비스 인터페이스 정의
 */
public interface MeetingService {

    void registerMeeting(MeetingRegisterPostReq meetingInfo, User user);
    List<MeetingDetailGetRes> getAllMeeting();
    List<MeetingDetailGetRes> searchMeeting(String searchWord);
    Meeting modifyMeeting(MeetingRegisterPostReq meetingInfo, Long meetingId);
    void deleteMeeting(Meeting meeting);
}
