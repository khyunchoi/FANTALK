<template>
	<div>
    <div class="meeting-list-cards-container">
      <div>
        <h2 style="font-size: 1.5em;">팬미팅 목록</h2>
      </div>  
      <br>

      <div>
        <button
          v-for="meeting in meetingList" :key="meeting"
          class="meeting-list-card"
          @click="enterMeeting(meeting.id)"
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
  export default {
    name: 'MeetingList',
    data: function() {
      return {
        meetingList: {}
      }
    },
    methods:{
      enterMeeting: function (idx) {
        this.$router.push({name:'MeetingRoom', params:{ meetingId:idx }})
      }
    },
    created: function () {
      this.$axios({
        method: 'get',
        url: 'http://localhost:8080/api/v1/meetings'
      })
      .then(res => {
        return res.data
      })
      .then(res => {
        this.meetingList = res
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