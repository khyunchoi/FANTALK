<template>
	<div>
    <img src="../assets/sidemymeetinglist.png" alt="mymeetinglist" style="padding: 6% 0 2% 2%;" @click="moveMyMeetingList">
    <div>
      <h2 style="padding: 2% 0 2% 16%; font-size: 1.5em;">신청한 팬미팅 목록</h2>
      <template>
        <div style="padding: 0.7% 5% 0.7% 10%;">
          <v-data-table
            :headers="headers"
            :items="meetings"
            :items-per-page="5"
            class="elevation-1"
          >
            <template v-slot:items="props">
              <td style="text-align: center;">{{ props.item.title }}</td>
              <td style="text-align: center;">{{ props.item.openDate }}</td>
              <td style="text-align: center;">{{ props.item.maxUser }}</td>
            </template>
          </v-data-table>
        </div>
      </template>
    </div>
	</div>
</template>

<script>
  export default {

    name: 'MyMeetingList',
     data () {
      return {
        search: '',
        pagination: {},
        selected: [],
        headers: [
          {
            text: '제목',
            value: 'title',
            sortable: false,
          },
          { 
            text: '날짜/시간', 
            value: 'openDate',
            sortable: false,
          },
          { 
            text: '신청인원', 
            value: 'maxUser',
            sortable: false,
          },
        ],
        meetings: []
      }
    },
    methods : {
      moveMyMeetingList: function () {
        this.$router.push({name: 'MyMeetingList'})
      },
      moveCreateMeeting: function () {
        this.$router.push({name: 'CreateMeeting'})
      }
    },
    created: function () {
      this.$axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/meetings/me`,
      })
      .then(res => {
        console.log(res)
        this.meetings = res.data
      })
      .catch(err => {
        console.log(err)
      })
    },
  }
</script>

<style scoped>
 img:hover {
   cursor: pointer;
 }
</style>