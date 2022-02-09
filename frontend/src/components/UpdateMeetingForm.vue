<template>
	<div data-app>
    <template>
        <v-text-field
          v-model="title"
          :counter="50"
          :rules="titleRules"
          label="팬미팅 제목"
          solo
          required
        ></v-text-field>

        <v-row>
          <v-col
            cols="12"
            sm="6"
            md="6"
          >
            <h5>날짜/시간</h5>
            <v-card style="padding: 3px; height: 32px;">
              <input style="border: 1px solid #979797;" type="date" v-model="date">
              <input style="border: 1px solid #979797; margin-left: 5px;" type="time" step="3600000" v-model="time">
            </v-card>
          </v-col>
          
          <v-col
            cols="12"
            sm="6"
            md="6"
          >
            <h5>인원</h5>
            <v-card style="padding: 3px; height: 32px;">
              <input style="width: 100%;" type="number" v-model="maxUser">
            </v-card>
          </v-col>
        </v-row>
        
        <div style="display: flex; justify-content: center; margin-top: 30px;">
          <v-btn @click="goBack()" style="background-color: #979797; color: #FFFFFF; margin: 0 10px;">취소</v-btn>
          <v-btn @click="submit()" style="background-color: #797BF8; color: #FFFFFF; margin: 0 10px;">수정</v-btn>
        </div>
    </template>
	</div>
</template>

<script>
  export default {
    name: 'UpdateMeetingForm',
    data: function() {
      return {
        meetingId: '',
        title: '',
        titleRules: [
          v => !!v || '제목은 필수입니다.',
          v => (v && v.length <= 50) || '제목은 50자 이하이어야 합니다.'
        ],
        date: '',
        time: '',
        maxUser: '',
        openDate: '',
      }
    },

    methods: {
      goBack () {
        this.$router.push({ name: 'MyMeetingList' })
      },
      submit () {
        const meetingItem = {
          title: this.title,
          openDate: this.date + ' ' + this.time,
          maxUser: this.maxUser
        }
        if (meetingItem.title) {
          this.$axios({
            method: 'post',
            url: `http://localhost:8080/api/v1/meetings/${this.meetingId}`,
            data: meetingItem,
          })
          .then(res => {
            console.log(res)
            this.title = ''
            this.openDate = ''
            this.maxUser = ''
            this.$router.push({ name: 'MyMeetingList' })
          })
          .catch(err => {
            console.log(meetingItem)
            console.log(err)
          })
        }
      }
    },

    created: function () {
      this.meetingId = this.$route.params.meetingId

      this.$axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/meetings/me/${this.meetingId}`,
      })
      .then(res => {
        this.title = res.data.title
        this.date = res.data.openDate.slice(0, 10)
        this.time = res.data.openDate.slice(11, 16)
        this.maxUser = res.data.maxUser
      })
      .catch(err => {
        console.log(err)
      })
    }
  }
</script>