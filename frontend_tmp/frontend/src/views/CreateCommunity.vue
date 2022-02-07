<template>
  <div style="margin: 20px;">
    <CreateCommunityForm />
  </div>
</template>

<script>
  import CreateCommunityForm from '../components/CreateCommunityForm.vue'

  export default {
    name: 'CreateCommunity',
    components: {
      CreateCommunityForm,
    },
    data: () => ({
    }),
  }
</script>