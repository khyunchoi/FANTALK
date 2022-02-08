<template>
  <div>
    <template>
      <v-form
        ref="form"
        v-model="valid"
        lazy-validation
      >

        <v-text-field
          v-model="title"
          :counter="50"
          :rules="titleRules"
          label="제목"
          solo
          required
        ></v-text-field>

        <v-textarea
          v-model="content"
          label="내용"
          rows="10"
          row-height="60"
          solo
        ></v-textarea>

        <v-file-input
          accept="image/*"
          label="File input"
          outlined
          dense
        ></v-file-input>

        <div style="display: flex; justify-content: center;">
          <v-btn @click="goBack()" style="background-color: #979797; color: #FFFFFF; margin: 0 10px;">취소</v-btn>
          <v-btn @click="submit()" style="background-color: #797BF8; color: #FFFFFF; margin: 0 10px;">수정</v-btn>
        </div>
      </v-form>
    </template>
  </div>
</template>

<script>
  export default {
    name: 'UpdateArticleForm',

    data: () => ({
      valid: true,
      communityId: '',
      articleId: '',
      title: '',
      titleRules: [
        v => !!v || '제목은 필수입니다.',
        v => (v && v.length <= 50) || '제목은 50자 이하이어야 합니다.'
      ],
      content: '',
    }),

    methods: {
      goBack() {
        this.$router.push({ name: 'DetailArticle', params: {communityId: this.communityId, articleId: this.articleId} })
      },
      submit () {
        this.$v.$touch()
      },
    },

    created() {
      this.communityId = this.$route.params.communityId
      this.articleId = this.$route.params.articleId
    }
  }
</script>