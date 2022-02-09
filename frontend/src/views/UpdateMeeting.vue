<template>
  <div style="display: flex; justify-content: center;">
    <div style="margin: 20px; padding: 20px; width: 700px;">
      <h3 style="margin-bottom: 20px; padding-bottom: 10px;">
        팬미팅 신청 수정
      </h3>
      <div style="display: flex; justify-content: center;">
        <v-card
          outlined
          style="padding: 20px; border: 1px solid #979797; width: 700px;"
        >
          <UpdateMeetingForm />
        </v-card>
      </div>
    </div>
  </div>
</template>

<script>
  import UpdateMeetingForm from '../components/UpdateMeetingForm.vue'
  export default {
    name: 'UpdateMeeting',
    components: {
      UpdateMeetingForm
    },
  }
</script>