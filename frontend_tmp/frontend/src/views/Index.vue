<template>
  <div>
    <Nav />
    <Body />
    <Footer />
  </div>
</template>

<script>
  import Nav from '../components/Nav.vue'
  import Body from '../components/Body.vue'
  import Footer from '../components/Footer.vue'

  export default {
    name: 'Index',
    components: {
      Nav, Body, Footer
    },
    data: () => ({
    }),
  }
</script>