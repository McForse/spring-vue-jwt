<template>
	<v-app>
		<NavigationDrawer/>

		<AppBar/>

		<v-main>
			<v-container>
				<transition name="fade">
					<router-view/>
				</transition>
			</v-container>
		</v-main>

		<LogoutDialog/>

		<v-footer
			app
			color="primary"
		>
			<v-spacer/>
			<span class="white--text">&copy; {{ new Date().getFullYear() }}</span>
		</v-footer>

		<Snackbar/>
	</v-app>
</template>

<script>
	import NavigationDrawer from '@/components/NavigationDrawer'
	import AppBar from '@/components/AppBar'
	import LogoutDialog from '@/components/LogoutDialog'
	import Snackbar from '@/components/Snackbar'

	export default {
		name: 'MainLayout',
		components: {
			LogoutDialog,
			AppBar,
			NavigationDrawer,
			Snackbar,
		},
	}
</script>

<style>
	.fade-enter-active, .fade-leave-active {
		transition-property: opacity;
		transition-duration: .25s;
	}

	.fade-enter-active {
		transition-delay: .25s;
	}

	.fade-enter, .fade-leave-active {
		opacity: 0
	}
</style>
