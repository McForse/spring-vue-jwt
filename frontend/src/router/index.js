import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'

Vue.use(Router)

const router = new Router({
	mode: 'history',
	routes: [
		{
			path: '/',
			name: 'Home',
			component: () => import('../views/Home'),
		},
		{
			path: '/login',
			name: 'SignIn',
			component: () => import('../views/SignIn'),
			meta: {
				requiresVisitor: true,
			},
		},
		{
			path: '/register',
			name: 'SignUp',
			component: () => import('../views/SignUp'),
			meta: {
				requiresVisitor: true,
			},
		},
		{
			path: '/user',
			name: 'UserPage',
			component: () => import('../views/UserPage'),
			meta: {
				requiresAuth: true,
			},
		},
		{
			path: '/admin',
			name: 'AdminPage',
			component: () => import('../views/AdminPage'),
			meta: {
				requiresAuth: true,
			},
		},
		{
			path: '*',
			redirect: '/',
		},
	],
})

router.beforeEach((to, from, next) => {
	if (to.matched.some(record => record.meta.requiresAuth)) {
		if (!store.getters.isAuthenticated) {
			next({
				path: '/login',
			})
		} else {
			next()
		}
	} else if (to.matched.some(record => record.meta.requiresVisitor)) {
		if (store.getters.isAuthenticated) {
			next({
				path: '/',
			})
		} else {
			next()
		}
	} else {
		next()
	}
})

export default router
