<template>
  <div>
    <h1 style="margin-bottom: 30px; padding-bottom: 10px; border-bottom: 1px solid #979797;">
      팬 커뮤니티 등록
    </h1>
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
          solo
          required
        ></v-text-field>

        <v-text-field
          v-model="title"
          :counter="30"
          :rules="titleRules"
          label="제목"
          solo
          required
        ></v-text-field>

        <v-file-input
          v-model="files"
          accept="image/*"
          label="File input"
          outlined
          dense
        ></v-file-input>
        
        <div style="display: flex; justify-content: center;">
          <v-btn @click="goBack()" style="background-color: #979797; color: #FFFFFF; margin: 0 10px;">취소</v-btn>
          <v-btn @click="submit()" style="background-color: #797BF8; color: #FFFFFF; margin: 0 10px;">등록</v-btn>
        </div>
      </v-form>
    </template>
  </div>
</template>

<script>
  export default {
    name: 'CreateCommunityForm',

    data: function() {
      return {
        valid: true,
        name: '',
        nameRules: [
          v => !!v || '이름은 필수입니다.',
          v => (v && v.length <= 10) || '이름은 10자 이하이어야 합니다.'
        ],
        title: '',
        titleRules: [
          v => !!v || '제목은 필수입니다.',
          v => (v && v.length <= 30) || '제목은 30자 이하이어야 합니다.'
        ],
        files: [],
      }
    },

    methods: {
      goBack() {
        this.$router.push({ name: 'Index' })
      },
      submit () {
        // const formData = new FormData();
        // formData.append('name', this.name);
        // formData.append('title', this.title);
        // formData.append('logoImage', this.files);
        // if (formData) {
        //   this.$axios({
        //       method: 'post',
        //       url: `http://127.0.0.1:8080/api/v1/communities/`,
        //       data: formData,
        //       headers: {
        //         'Content-Type': 'multipart/form-data'
        //       }
        //     })
        //     .then(res => {
        //       console.log(res)
        //       this.name = ''
        //       this.title = ''
        //       this.files = ''
        //       this.$router.push({ name: 'Index' })
        //     })
        //     .catch(err => {
        //       console.log(formData)
        //       console.log(err)
        //   })
        // }

        const communityItem = {
          name: this.name,
          title: this.title,
          logoImage: this.files
        }
        if (communityItem.name) {
          this.$axios({
              method: 'post',
              url: `http://127.0.0.1:8080/api/v1/communities/`,
              data: communityItem,
            })
            .then(res => {
              console.log(res)
              this.name = ''
              this.title = ''
              this.files = ''
              this.$router.push({ name: 'Index' })
            })
            .catch(err => {
              console.log(communityItem)
              console.log(err)
          })
        }
      },
    }
  }
</script>