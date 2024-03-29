import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/Index.vue'
import Login from '@/views/Login.vue'
import Signup from '@/views/Signup.vue'
import CommunityList from '../views/CommunityList.vue'
import CommunityListItem from '../views/CommunityListItem.vue'
import CreateCommunity from '../views/CreateCommunity.vue'
import CreateArticle from '../views/CreateArticle.vue'
import DetailArticle from '../views/DetailArticle.vue'
import UpdateArticle from '../views/UpdateArticle.vue'
import MeetingList from '../views/MeetingList.vue'
import CheckManager from '../views/CheckManager.vue'
import CreateMeeting from '../views/CreateMeeting.vue'
import MyMeetingList from '../views/MyMeetingList.vue'
import DetailMyMeeting from '../views/DetailMyMeeting.vue'
import UpdateMeeting from '../views/UpdateMeeting.vue'
import MeetingRoom from '../views/MeetingRoom.vue'
import MeetingGuide from '../views/MeetingGuide.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup,
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
  {
    path: '/meetings',
    name: 'MeetingList',
    component: MeetingList,
  },
  {
    path: '/check-manager',
    name: 'CheckManager',
    component: CheckManager,
  },
  {
    path: '/meetings/create',
    name: 'CreateMeeting',
    component: CreateMeeting,
  },
  {
    path: '/meetings/me',
    name: 'MyMeetingList',
    component: MyMeetingList,
  },
  {
    path: '/meetings/me/:meetingId',
    name: 'DetailMyMeeting',
    component: DetailMyMeeting,
  },
  {
    path: '/meetings/:meetingId',
    name: 'MeetingRoom',
    component: MeetingRoom,
  },
  {
    path: '/meetings/:meetingId/update',
    name: 'UpdateMeeting',
    component: UpdateMeeting,
  },
  {
    path: '/meetings/:meetingId/guide',
    name: 'MeetingGuide',
    component: MeetingGuide,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
