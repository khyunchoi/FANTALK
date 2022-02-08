package com.ssafy.api.service;

import com.ssafy.api.request.MeetingRegisterPostReq;
import com.ssafy.api.response.MeetingDetailGetRes;
import com.ssafy.db.entity.Meeting;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * 팬미팅 서비스 구현체 정의
 */
@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService{

    private final MeetingRepository meetingRepository;

    // 팬미팅 등록
    @Override
    public void registerMeeting(MeetingRegisterPostReq meetingInfo, User user) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(meetingInfo.getOpenDate(), formatter);

        try {
            Meeting meeting = Meeting.builder()
                    .title(meetingInfo.getTitle())
                    .maxUser(meetingInfo.getMaxUser())
                    .openDate(dateTime)
                    .isActive(false)
                    .user(user)
                    .build();
            meetingRepository.save(meeting);
        } catch (Exception e) {
            throw e;
        }
    }

    // 모든 팬미팅 목록 조회
    @Override
    public List<MeetingDetailGetRes> getAllMeeting() {

        List<Meeting> findMeetings = meetingRepository.findAll();
        List<MeetingDetailGetRes> meetings = new ArrayList<>();
        for (Meeting findMeeting : findMeetings) {
            MeetingDetailGetRes meeting = new MeetingDetailGetRes();
            meeting.setId(findMeeting.getId());
            meeting.setTitle(findMeeting.getTitle());
            meeting.setOpenDate(findMeeting.getOpenDate());
            meetings.add(meeting);
        }
        return meetings;
    }

    // 팬미팅 제목을 기반으로 검색
    @Override
    public List<MeetingDetailGetRes> searchMeeting(String searchWord) {

        List<Meeting> findMeetings = meetingRepository.findByTitleContaining(searchWord);
        List<MeetingDetailGetRes> meetings = new ArrayList<>();
        for (Meeting findMeeting : findMeetings) {
            MeetingDetailGetRes meeting = new MeetingDetailGetRes();
            meeting.setId(findMeeting.getId());
            meeting.setTitle(findMeeting.getTitle());
            meeting.setOpenDate(findMeeting.getOpenDate());
            meetings.add(meeting);
        }
        return meetings;
    }

    // 팬미팅 수정
    @Override
    public Meeting modifyMeeting(MeetingRegisterPostReq meetingInfo, Long meetingId) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(meetingInfo.getOpenDate(), formatter);

        try {
            Meeting meeting = meetingRepository.findById(meetingId).get();
            meeting.changeTitleAndOpenDateAndMaxUser(meetingInfo.getTitle(), dateTime, meetingInfo.getMaxUser());
            meetingRepository.save(meeting);
            return meeting;
        } catch (Exception e) {
            throw e;
        }
    }

    // 팬미팅 삭제
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetingRepository.delete(meeting);
    }

}
