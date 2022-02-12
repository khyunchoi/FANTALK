<template>
	<div>
    <template>
        <v-text-field
          v-model="title"
          :counter="50"
          :rules="titleRules"
          label="팬미팅 제목"
          solo
          required
        ></v-text-field>

        <v-row style="border-bottom: 1px solid #979797; margin: 0px 0px 10px 0px;">
          <v-col
            cols="12"
            sm="6"
            md="6"
            style="padding: 12px 12px 12px 0px;"
          >
            <h5>날짜/시간</h5>
            <v-card style="padding: 3px; height: 32px;">
              <input style="border: 1px solid #979797;" type="date" v-model="date">
              <input style="border: 1px solid #979797; margin-left: 4px;" type="time" step="3600000" v-model="time">
            </v-card>
          </v-col>
          
          <v-col
            cols="12"
            sm="6"
            md="6"
            style="padding: 12px 0px 12px 12px;"
          >
            <h5>인원</h5>
            <v-card style="padding: 3px; height: 32px;">
              <input style="width: 100%;" type="number" v-model="maxUser">
            </v-card>
          </v-col>
        </v-row>

        <h3 style="color: #FF0000;">
          **안내사항
        </h3>
        <ul style="list-style-type: square; color: #979797; margin-left: 20px; margin-top: 10px;">
          <li>안내사항1</li>
          <li>안내사항2</li>
          <li>안내사항3</li>
          <li>안내사항4</li>
          <li>안내사항5</li>
        </ul>
        
        <div style="display: flex; justify-content: center; margin-top: 30px;">
          <v-btn @click="goBack()" style="background-color: #979797; color: #FFFFFF; margin: 0 10px;">취소</v-btn>
          <v-btn @click="submit()" style="background-color: #797BF8; color: #FFFFFF; margin: 0 10px;">신청</v-btn>
        </div>
    </template>
	</div>
</template>

<script>
  const SERVER_URL = process.env.VUE_APP_API_URL
  export default {
    name: 'CreateMeetingForm',
    data: function() {
      return {
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
      setToken () {
        const token = localStorage.getItem('jwt')
        const config = {
          Authorization: `Bearer ${token}`
        }
        return config
      },
      goBack () {
        this.$router.push({ name: 'MyMeetingList' })
      },
      submit () {
        const meetingItem = {
          title: this.title,
          openDate: this.date + ' ' + this.time,
          maxUser: this.maxUser,
        }
        if (meetingItem.title) {
          this.$axios({
            method: 'post',
            url: `${SERVER_URL}/api/v1/meetings`,
            data: meetingItem,
            headers: this.setToken(),
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
  }
</script>