<template>
  <div>
    <h1>팬 커뮤니티 등록</h1>
    <template>
      <v-form
        ref="form"
        v-model="valid"
        lazy-validation
      >
        <v-text-field
          v-model="name"
          :counter="10"
          :rules="nameRules"
          label="이름"
          required
        ></v-text-field>

        <v-text-field
          v-model="title"
          :counter="30"
          :rules="titleRules"
          label="제목"
          required
        ></v-text-field>

        <v-btn style="background-color: #979797; color: #FFFFFF;">취소</v-btn>
        <v-btn @click="submit" style="background-color: #797BF8; color: #FFFFFF;">등록</v-btn>
      </v-form>
    </template>
  </div>

</template>

<script>
  export default {
    name: 'CreateCommunityForm',

    data: () => ({
      valid: true,
      name: '',
      nameRules: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 10) || 'Name must be less than 10 characters'
      ],
      title: '',
      titleRules: [
        v => !!v || 'title is required',
        v => (v && v.length <= 30) || 'Title must be less than 30 characters'
      ],
    }),

    methods: {
      submit () {
        this.$v.$touch()
      },
    }
  }
</script>