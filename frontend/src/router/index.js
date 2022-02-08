import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/Index.vue'
import CommunityList from '../views/CommunityList.vue'
import CommunityListItem from '../views/CommunityListItem.vue'
import CreateCommunity from '../views/CreateCommunity.vue'
import CreateArticle from '../views/CreateArticle.vue'
import DetailArticle from '../views/DetailArticle.vue'
import UpdateArticle from '../views/UpdateArticle.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index,
  },
  {
    path: '/communities',
    name: 'CommunityList',
    component: CommunityList,
  },
  {
    path: '/communities/create',
    name: 'CreateCommunity',
    component: CreateCommunity,
  },
  {
    path: '/communities/:communityId',
    name: 'CommunityListItem',
    component: CommunityListItem,
  },
  {
    path: '/communities/:communityId/articles/create',
    name: 'CreateArticle',
    component: CreateArticle,
  },
  {
    path: '/communities/:communityId/articles/:articleId',
    name: 'DetailArticle',
    component: DetailArticle,
  },
  {
    path: '/communities/:communityId/articles/:articleId/update',
    name: 'UpdateArticle',
    component: UpdateArticle,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
