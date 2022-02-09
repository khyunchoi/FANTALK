<template>
  <div>
    <img src="../assets/sidemymeetinglist.png" alt="check-manager" style="padding: 6% 0 2% 2%;">
    <div style="display: flex; justify-content: center;">
      <div style="margin: 20px; padding: 20px; width: 700px;">
        <h3 style="margin-bottom: 20px; padding-bottom: 10px;">
          팬미팅 신청
        </h3>
        <div style="display: flex; justify-content: center;">
          <v-card
            style="padding: 20px; border: 1px solid #979797; width: 700px;"
          >
            <CreateMeetingForm />
          </v-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import CreateMeetingForm from '../components/CreateMeetingForm.vue'
  export default {
    name: 'CreateMeeting',
    components: {
      CreateMeetingForm
    },
  }
</script>