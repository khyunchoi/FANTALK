<template>
	<div>
    <div class="meeting-list-cards-container">
      <div style="display: flex;">
        <div>
          <h2 style="font-size: 1.5em;">팬미팅 목록</h2>
        </div>
        <v-text-field
          label="제목"
          dense
          solo
          v-model="q"
        >
        </v-text-field>
        <v-btn @click="search()" style="background-color: #979797; color: white;">
          검색
        </v-btn>
      </div>
      

      <div>
        <button
          v-for="meeting in meetingList" :key="meeting"
          class="meeting-list-card"
          @click="enterMeetingGuide(meeting.id)"
        >
          {{ meeting.title }}
          <br>
          <br>
          {{ meeting.openDate }}
        </button>
      </div>
      

    </div>
	</div>
</template>

<script>
  const SERVER_URL = process.env.VUE_APP_API_URL
  export default {
    name: 'MeetingList',
    data: function() {
      return {
        meetingList: {},
        q: ''
      }
    },
    methods:{
      enterMeetingGuide: function (idx) {
        this.$router.push({name:'MeetingGuide', params:{ meetingId:idx }})
      },
      search() {
        this.$axios({
          method: 'get',
          url: `${SERVER_URL}/api/v1/meetings/search`,
          params: {
            q: this.q,
          }
        })
        .then(res => {
          this.meetingList = res.data
        })
        .catch(err => {
          console.log(qItem)
          console.log(err)
        })
      }
    },
    created: function () {
      this.$axios({
        method: 'get',
        url: `${SERVER_URL}/api/v1/meetings`
      })
      .then(res => {
        this.meetingList = res.data
        console.log(this.meetingList)
        return this.meetingList
      })
      .catch(err => {
          console.log(err)
      })
    }
  }
</script>

<style>
  .meeting-list-cards-container {
    width: 100%;
    display: flex;
    padding: 10%;
    flex-direction: column;
    align-items: left;
  }

  .meeting-list-card {
    width: 20%;
    padding: 3%;
    border-style: solid;
    border-width: 5px;
    border-color: #797BF8;
    margin: 10px;
  }
</style>