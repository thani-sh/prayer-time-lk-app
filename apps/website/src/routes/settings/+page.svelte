<script lang="ts">
	import { CITIES, METHODS, type City, type Method } from '@thani-sh/prayer-time-lk';
	import capitalize from 'lodash/capitalize';
	import { city } from '$lib/domain/PrayerTimeCity';
	import { method } from '../../lib/domain/PrayerTimeMethod';
	import { CodeIcon, MailIcon, CookieIcon } from 'lucide-svelte';
</script>

{#snippet Dropdown(
	label: string,
	options: readonly string[],
	selected: string,
	onSelect: (value: string) => void
)}
	<fieldset class="fieldset my-2">
		<legend class="fieldset-legend">{label}</legend>
		<select
			class="select select-lg w-full"
			on:change={(e) => onSelect((e.target as HTMLSelectElement).value)}
		>
			{#each options as option}
				<option value={option} selected={selected === option}>{capitalize(option)}</option>
			{/each}
		</select>
	</fieldset>
{/snippet}

<div class="flex flex-col w-full max-w-md mx-auto px-4 py-4 mb-20">
	<section>
		<h1 class="text-3xl font-light mb-2">Preferences</h1>
		<div class="card bg-black/10 shadow-sm p-4">
			<p class="text-sm">
				The prayer times displayed in the app are calculated using these settings.
			</p>
			{@render Dropdown('City', CITIES, $city, (value) => city.set(value as City))}
			{@render Dropdown('Method', METHODS, $method, (value) => method.set(value as Method))}
			<p class="text-sm mt-2">
				This app provides accurate Islamic prayer times sourced from local mosques.
			</p>
		</div>
	</section>

	<section class="mt-8">
		<h1 class="text-3xl font-light mb-2">About this website</h1>
		<div class="card bg-black/10 shadow-sm p-4">
			<p class="text-sm">
				The app is open-source and free to use under the MIT license, inviting everyone to
				contribute or share feedback to help improve it. You can explore the source code or
				contribute via its GitHub repository.
			</p>

			<a href="https://github.com/thani-sh/prayer-time-lk" class="btn btn-ghost justify-start mt-2">
				<CodeIcon class="w-4 h-4 mr-2" />
				Open the Github repository
			</a>

			<a href="mailto:contact@prayertime.lk" class="btn btn-ghost justify-start mt-2">
				<MailIcon class="w-4 h-4 mr-2" />
				Report errors and feedback
			</a>

			<p class="text-sm mt-4">
				Your privacy is important to us! By using this app, you agree to our Privacy Policy — please
				read it.
			</p>

			<a href="/docs/privacy" class="btn btn-ghost justify-start mt-2">
				<CookieIcon class="w-4 h-4 mr-2" />
				Open the privacy policy
			</a>
		</div>
	</section>
</div>
