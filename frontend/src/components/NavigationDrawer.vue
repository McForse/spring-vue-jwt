<template>
	<v-navigation-drawer
		app
		clipped
		v-model="drawer"
		:mini-variant="drawerMini"
	>

		<v-list-item
			v-if="this.$store.getters.isAuthenticated"
			class="px-2"
			two-line
		>
			<v-list-item-avatar>
				<v-avatar color="indigo" size="40">
					<span class="white--text headline">{{ this.$store.getters.getUsername | initials}}</span>
				</v-avatar>
			</v-list-item-avatar>

			<v-list-item-content>
				<v-list-item-title>{{ this.$store.getters.getUsername }}</v-list-item-title>
				<v-list-item-subtitle>Logged In</v-list-item-subtitle>
			</v-list-item-content>
		</v-list-item>

		<v-list-item
			v-else-if="this.$vuetify.breakpoint.mobile"
		>
			<v-list-item-content>
				<v-list-item-title class="title">
					Application
				</v-list-item-title>
				<v-list-item-subtitle>
					Menu
				</v-list-item-subtitle>
			</v-list-item-content>
		</v-list-item>

		<v-divider/>

		<v-list dense nav>
			<v-list-item
				v-for="item in menuItems"
				:key="item.title"
				:to="item.path"
				link
			>
				<v-list-item-icon>
					<v-icon>{{ item.icon }}</v-icon>
				</v-list-item-icon>

				<v-list-item-content>
					<v-list-item-title>{{ item.title }}</v-list-item-title>
				</v-list-item-content>
			</v-list-item>
		</v-list>

		<template v-slot:append v-if="this.$store.getters.isAuthenticated">
			<v-divider/>
			<v-list dense nav>
				<v-list-item
					@click="showLogoutDialog"
				>
					<v-list-item-icon>
						<v-icon>mdi-exit-to-app</v-icon>
					</v-list-item-icon>

					<v-list-item-content>
						<v-list-item-title>Logout</v-list-item-title>
					</v-list-item-content>
				</v-list-item>
			</v-list>
		</template>
	</v-navigation-drawer>
</template>

<script>
	import {EventBus} from '@/main'

	export default {
		name: 'NavigationDrawer',
		data: () => ({
			drawer: null,
			drawerMini: false,
		}),
		created() {
			EventBus.$on('toggleDrawer', () => {
				this.drawerLogic()
			})
		},
		computed: {
			menuItems() {
				if (this.$store.getters.isAuthenticated) {
					let menu = [
						{title: 'Home', icon: 'mdi-home', path: '/home'},
						{title: 'User', icon: 'mdi-account', path: '/user'},
					]

					if (this.$store.getters.isAdmin) {
						menu.push({title: 'Admin', icon: 'mdi-key', path: '/admin'})
					}

					return menu
				}
				return [
					{title: 'Home', icon: 'mdi-home', path: '/'},
					{title: 'Login', icon: 'mdi-lock-open', path: '/login'},
					{title: 'Register', icon: 'mdi-account-plus', path: '/register'},
				]
			},
		},
		methods: {
			showLogoutDialog() {
				EventBus.$emit('showLogoutDialog')
			},
			drawerLogic() {
				if (this.$vuetify.breakpoint.mobile) {
					this.drawerMini = false
					this.drawer = !this.drawer
				} else {
					this.drawerMini = !this.drawerMini
				}
			},
		},
		filters: {
			initials: function (value) {
				return value.match(/\b(\w)/g).join().toUpperCase()
			},
		},
	}
</script>

<style>
</style>