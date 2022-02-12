<template>
  <div>
    <img src="../assets/sidemymeetinglist.png" alt="check-manager" style="padding: 6% 0 2% 2%;">
    <div style="display: flex; justify-content: center;">
      <div style="margin: 20px; padding: 20px; width: 700px;">
        <h3 style="margin-bottom: 20px; padding-bottom: 10px;">
          팬미팅 상세 조회
        </h3>
        <div style="display: flex; justify-content: center;">
          <v-card
            style="padding: 20px; border: 1px solid #979797; width: 700px;"
          >
            <template>
              <h3 style="margin-top: 10px; margin-bottom: 10px;">
                {{ title }}
              </h3>

              <v-row style="border-bottom: 1px solid #979797; margin: 0px 0px 10px 0px;">
                <v-col
                  cols="12"
                  sm="6"
                  md="6"
                  style="padding: 12px 12px 12px 0px;"
                >
                  <h5>날짜/시간</h5>
                  <v-card outlined style="padding: 3px; height: 32px;">
                    <div>
                      {{ openDate }}
                    </div>
                  </v-card>
                </v-col>
                
                <v-col
                  cols="12"
                  sm="6"
                  md="6"
                  style="padding: 12px 0px 12px 12px;"
                >
                  <h5>인원</h5>
                  <v-card outlined style="padding: 3px; height: 32px;">
                    <div>
                      {{ maxUser }}
                    </div>
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
              
              <div style="display: flex; justify-content: end; margin-top: 30px;">
                <v-btn @click="edit()" style="background-color: #797BF8; color: #FFFFFF; margin: 0 10px;">수정</v-btn>
              </div>
            </template>
          </v-card>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  const SERVER_URL = process.env.VUE_APP_API_URL
  export default {
    name: 'DetailMyMeeting',
    data: function() {
      return {
        meetingId: '',
        title: '',
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
      edit () {
        this.$router.push({ name: 'UpdateMeeting' })
      }
    },

    created: function () {
      this.meetingId = this.$route.params.meetingId

      this.$axios({
        method: 'get',
        url: `${SERVER_URL}/api/v1/meetings/me/${this.meetingId}`,
        headers: this.setToken(),
      })
      .then(res => {
        this.title = res.data.title
        this.openDate = res.data.openDate.slice(0, 16)
        this.maxUser = res.data.maxUser
      })
      .catch(err => {
        console.log(err)
      })
    }
  }
</script>