package com.example.Kanaeru_Back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "RESERVATIONS")
public class ReservationEntity {
    
    @Id
    @Column(name = "RESERVATION_ID", length = 36, nullable = false)
    private String reservationId;
    
    @Column(name = "USER_ID", length = 36, nullable = false)
    private String userId;
    
    @Column(name = "SELECTED_USER_ID", length = 36, nullable = false)
    private String selectedUserId;
    
    @Column(name = "START_AT", nullable = false)
    private LocalDateTime startAt;
    
    @Column(name = "END_AT", nullable = false)
    private LocalDateTime endAt;
    
    @Column(name = "CONTENT", length = 400, nullable = false)
    private String content;
    
    @Column(name = "APPROVAL_FLG", length = 1, nullable = false)
    private String approvalFlg;
    
    @Column(name = "CALENDAR_ID", length = 64)
    private String calendarId;
    
    @Column(name = "CALENDAR_EVENT_ID", length = 128)
    private String calendarEventId;
    
    @Column(name = "GOOGLE_MEET_JOIN_URL", length = 2048)
    private String googleMeetJoinUrl;
    
    @Column(name = "GOOGLE_MEET_ID", length = 128)
    private String googleMeetId;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;
}
